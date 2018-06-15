# SpringBase project 

A simple of Java Enterprise Editon + hibernate + Spring framework + Glassfish



## Download eclipse packages

- JST Server Adapters
- Web Tools Platform (WTP) (Menu)
- Project Provided Components (Menu)

## Install or update Eclipse JST Server Adapters

For install Eclipse JST Server Adapters, follow the next steps:

1. Open Help menu
2. Open Eclipse Marketplace option
3. Find "Eclipse JST Server Adapters (Apache Tomcat, JOnAS, J2EE)" Luna

Add tomcat version to the environment, follow the next steps:

1. Open Window menu
2. Open preferneces
3. Open Server
4. Open Runtime Environment
5. Add apache tomcat version

## Add tomcat server

Show the server tab to add a tomcat server.

1. Open window
2. Open show view
3. Click in servers

Add tomcat server from servers list in the button "add server"

Note: if the serves isn't running, try to change the number port of 8080 to 8082 opening the config/server.xml file.


# Install hibernate

## Download from official web page

1. Download hibernate from the official web site [hibernate.org/orm](http://hibernate.org/orm/)
2. Unzip the folder files
3. Copy libraries files of /lib/required/ to /WebContent/WEB-INF/lib/

## Add jar files to project

1. Right click on project name located in Package Explorer tab.
2. Click in Java Build Path option.
3. Click in Libraries
4. Click in Add JARs... button

## Install hibernate Tools on eclipse

1. Download the file from the official web site [tools.jboss.org](https://tools.jboss.org/downloads/jbosstools/mars/4.3.1.Final.html#zips) 
2. Click on Artifacts tab
3. Download the zip file with name "Update site (including sources) bundle of all JBoss Core Tools"
4. Open Help menu in eclipse
5. Click in install new software option
6. Click in Add... button
7. Click in archive button
8. Find the local file with name "jbosstools-4.3.1.Final-updatesite-core"
9. Select the following files
	1. Abridged JBoss Tools
		1. Hiberante Tools
		2. JBoss Maven Hibernate Configuration
	2. JBoss Application Development
		1. Hiberante Tools
	3. JBoss Data Services Development
		1. Hibernate Tools
	4. JBoss Maven Support
		1. JBoss Maven Hibernate Configurator
	5. JBoss Web and Java EE Development
		1. Hibernate Tools.	
10. IMPORTANT: Unselect "Contact all updates sites during install to find required software"
10. Click on next button
11. Click on finish button

Note: can see the video [Hibernate Tutorial Part 2](https://www.youtube.com/watch?v=D-LKZEBzF8U) for more information.

You can check if Hibernate has been installed following the next steps.

1. Open Window menu
2. Click on perspective option
3. Click on open perspective
4. Click on other option
5. Find hibernate, if it options is showed then the install is correct.

Do

## Configure JDBC Connection to SQL Server.

### Download the libraries
1. Download "Microsoft JDBC Driver 6.4 for SQL Server" from the [jdbc-microsoft-driver-for-sql-server](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server) web site.
2. Save rar or zip folder
3. Unzip file
4. Copy the jar files into WebComponen/WEB-INFO/lib/sqlserver folder

### Add libraries to the project

1. Right click on project name located in Package Explorer tab.
2. Click in Java Build Path option.
3. Click in Libraries
4. Click on Add Library
5. Select User Library
6. Click on Next button
7. Click on User Libraries... button
8. Clikc on New button
9. Write SqlServer Library and click on Ok button
10. Click on Add JARs.. and find the sql server libraries at WebComponen/WEB-INFO/ folder
11. Click on Ok button
12. Click on finish button
13. Click on Ok button

### Install SQL Explore perspective

1. Open Help menu in eclipse
2. Click on install new software option
3. Click on Add... button
4. Click on 
5. Add the name "SQL Explorer" and url http://eclipsesql.sourceforge.net/
6. Click on OK
7. Select the last version 
8. Unselect "Contact all update sites during install to find required software"
9. Click on next
10. Accept the install terms
11. Click on finish.


### Add a Sql driver 

1. Open window menu
2. Click on preferences option
3. Click on "Data Management" option
4. Click on "Conectivity" option
5. Click on Driver Definitions
6. Click on Add... button
7. Select "Generic JDBC Driver" on "Name/Type" tab
8. Write the name "Sql server" in driver name input.
9. Click on JAR List tab
10. Click on Add JAR/Zip... button
11. Select "sqljdbc42.jar" file and click on open button
12. Click on "Properties" tab
13. fill connection URL with: jdbc:sqlserver://[instanceName];databaseName=[yourDatabaseName]
14. Fill Database name with: yourDatabaseName
15. Click the button located at the end of the row with name "..."
16. Select "Browse for class" option
17. Select the first class driver and click on Ok button
18. Fill user ID with "sa"
19. Click on OK button
20. Click on OK button

### Add property to hibernate.cfg.xml
add the following line down of hibernate.dialect property
```
current_session_context_class
```

### How to generate Hibernate mapping files & annotation with Hibernate Tools

https://www.mkyong.com/hibernate/how-to-generate-code-with-hibernate-tools/


### How to add user permissions to tomcat 7
An example of tomcat-users.xml file with the permissions added

```
<role rolename="admin-gui"/>
  <role rolename="manager-gui"/>
  <role rolename="manager-status"/>
  <role rolename="manager-script"/>
  <role rolename="manager-jmx"/>
  <user username="tomcat" password="tomcat" roles="admin-gui,manager-gui,manager-status,manager-script,manager-jmx"/>
```  
Reference:[tomcat-default-administrator-password](https://www.mkyong.com/tomcat/tomcat-default-administrator-password/)

# Download spring mvc jar files

Access to [springframework page](http://repo.spring.io/release/org/springframework/spring/5.0.6.RELEASE/), and find the last version of spring mvc, for this example the spring mvc version is "spring-framework-5.0.6.RELEASE-dist".  After that download the jar files, copy it in lib WEB-INF/folder



# Hibernate

* [hibernate circular foreign key](https://stackoverflow.com/questions/3513950/hibernate-circular-foreign-key)
* [LazyInitializationException](https://vladmihalcea.com/the-open-session-in-view-anti-pattern/)

## How to avoid cyclic reference

I have two entities, element and elementType, element is the main entity, it has an attribute named 'elementTypeId' and it's a foreign key.  To resolve the cyclic reference is required to delete the reference of the same element with the following code 'elementType.setElments(null)' into of setElementType method into ElementType entity class.   The complete code is:

```
// Element class

public void setEntiti(Entiti entiti) {
		entiti.setElements(null);
		this.entiti = entiti;
	}
```