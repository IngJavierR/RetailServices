# Ejercicios capacitación

```XML
<!-- Generar una tabla en Sql Server -->
<changeSet author="javierrodriguez" id="180119-01">
	<createTable tableName="C_PRUEBA">
		<column name="ID" type="INT">
			<constraints nullable="true" primaryKey="false" unique="false"/>
		</column>
		<column name="DS_NAME" type="VARCHAR(50)">
			<constraints nullable="true" primaryKey="false" unique="false"/>
		</column>
	</createTable>
</changeSet>

<!-- Generara una nueva tabla -->
<changeSet author="javierrodriguez" id="180119-02">
	<createTable tableName="K_PUESTO">
		<column autoIncrement="true" name="ID_PUESTO" type="int">
			<constraints primaryKey="true" primaryKeyName="PK_K_USER"/>
		</column>
		<column name="DS_NAME" type="nvarchar(30)">
			<constraints nullable="false"/>
		</column>
		<column name="DS_DESC" type="nvarchar(30)">
			<constraints nullable="false"/>
		</column>
		<column name="FG_ACTIVE" type="bit">
			<constraints nullable="false"/>
		</column>
		<column name="DT_LAST_MODIFICATION" type="datetime">
			<constraints nullable="false"/>
		</column>
		<column name="ID_LAST_USER_MODIFIER" type="int">
			<constraints nullable="false"/>
		</column>
	</createTable>
</changeSet>

<!-- Generara una segunda tabla -->
<changeSet author="javierrodriguez" id="180119-03">
	<createTable tableName="K_USER">
		<column autoIncrement="true" name="ID_USER" type="int">
			<constraints primaryKey="true" primaryKeyName="PK_K_USER"/>
		</column>
		<column name="DS_NAME" type="nvarchar(30)">
			<constraints nullable="false"/>
		</column>
		<column name="DS_USERNAME" type="nvarchar(30)">
			<constraints nullable="false"/>
		</column>
		<column name="DS_EMAIL" type="nvarchar(30)">
			<constraints nullable="false"/>
		</column>
		<column name="ID_PUESTO" type="INT">
			<constraints nullable="false"/>
		</column>
		<column name="FG_ACTIVE" type="bit">
			<constraints nullable="false"/>
		</column>
		<column name="DT_LAST_MODIFICATION" type="datetime">
			<constraints nullable="false"/>
		</column>
		<column name="ID_LAST_USER_MODIFIER" type="int">
			<constraints nullable="false"/>
		</column>
	</createTable>
</changeSet>

<!-- Generar constraints -->
<changeSet author="javierrodriguez" id="180119-04">
	<addForeignKeyConstraint baseColumnNames="ID_PUESTO" baseTableName="K_USER" constraintName="FK_K_USER_ID_PUESTO_K_PUESTO" deferrable="false" initiallyDeferred="false" onDelete="NO ACTION" onUpdate="NO ACTION" referencedColumnNames="ID_PUESTO" referencedTableName="K_PUESTO"/>
</changeSet>

<!-- Rollback -->
<!--
liquibase --changeLogFile="changesets/db.changelog-master.xml" rollbackCount 1
-->

<!-- Generar Inserts -->
<changeSet author="javierrodriguez" id="180119-05">
    <insert tableName="C_PRUEBA">
        <column name="ID" value="1"/>
        <column name="DS_NAME" value="Prueba1"/>
    </insert>
    <insert tableName="C_PRUEBA">
        <column name="ID" value="2"/>
        <column name="DS_NAME" value="Prueba2"/>
    </insert>
</changeSet>

<!-- Generar Stored Procedure -->
<changeSet author="javierrodriguez" id="180119-06">
	<createProcedure encoding="utf8" procedureName="getPrueba">
		CREATE PROCEDURE getPrueba
          AS
          BEGIN
            SELECT * FROM C_PRUEBA
          END;
    </createProcedure>
</changeSet>

<!-- Changelog de una bd existente -->
<!--
liquibase --changeLogFile="changesets/db.changelog-0.0.0.0.xml" generateChangeLog
-->

<!-- Create table by hand -->
<!--
CREATE TABLE Persons (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255) 
);
-->

<!-- Diferencias entre bd -->
<!--
liquibase --changeLogFile="changesets/db.changelog-0.0.0.2.xml" diffChangeLog
-->

<!-- Archivo de salida -->
<!--
liquibase --changeLogFile="changesets/db.changelog-master.xml" updateSQL > scripts.sql
-->
```