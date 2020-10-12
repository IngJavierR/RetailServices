package mx.com.axity.zuul.filters;

import com.google.common.net.HttpHeaders;
import com.netflix.zuul.context.RequestContext;
import mx.com.axity.zuul.BaseTest;
import mx.com.axity.zuul.services.ITokenService;
import mx.com.axity.zuul.to.ValidTokenRequestTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import static org.mockito.Mockito.when;

public class SimpleFilterTest extends BaseTest {

    @MockBean
    public ITokenService tokenService;

    @Before
    public void setTestRequestcontext() {
        RequestContext context = new RequestContext();
        RequestContext.testSetCurrentContext(context);
    }

    @After
    public void reset() {
        RequestContext.getCurrentContext().clear();
    }

    @Test
    public void returnNullFilterTest() throws Exception{
        SimpleFilter filter = createFilter("hello", null, new MockHttpServletResponse(),
                false, HttpMethod.GET, "/api/oauth/oauth", "123");
        Object result = filter.run();
        Assert.assertNull(result);
    }

    @Test
    public void runWithOriginContentLength() throws Exception {

        SimpleFilter filter = createFilter("hello", null, new MockHttpServletResponse(),
                false, HttpMethod.GET, "/api/oauth/oauthrs", "123");
        RequestContext.getCurrentContext().setOriginContentLength(6L); // for test
        RequestContext.getCurrentContext().setResponseGZipped(false);
        Object result = filter.run();
        Assert.assertNull(result);
    }

    private SimpleFilter createFilter(String content, String characterEncoding, MockHttpServletResponse response,
                                      boolean streamContent, HttpMethod method, String requestURI, String token) throws Exception {
        MockHttpServletRequest requestMock = new MockHttpServletRequest(method.toString(),requestURI);
        requestMock.addHeader("Authorization", "Bearer " + token);
        requestMock.addHeader("User", "Admin");

        HttpServletRequest request = requestMock;

        RequestContext context = new RequestContext();
        context.setRequest(request);
        context.setResponse(response);

        if (characterEncoding != null) {
            response.setCharacterEncoding(characterEncoding);
        }

        if (streamContent) {
            context.setResponseDataStream(new ByteArrayInputStream(content.getBytes(characterEncoding)));
        } else {
            context.setResponseBody(content);
        }

        context.addZuulResponseHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(content.length()));

        context.set("error.status_code", HttpStatus.NOT_FOUND.value());
        RequestContext.testSetCurrentContext(context);
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(tokenService.validateToken(new ValidTokenRequestTO())).thenReturn(responseEntity);
        SimpleFilter filter = new SimpleFilter();
        return filter;
    }

}
