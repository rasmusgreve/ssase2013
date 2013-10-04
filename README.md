ssase2013
=========

Server for SSAS E2013
Team 6
IP-adress : 192.237.204.230

Christian Lyngbye (clyn)
Jacob Fischer (jaco)
Rasmus Greve (ragr)
Ivaylo Sharkov (isha)

Installation instructions:
* Run table_structure.sql
* Run table_data.sql
* Setup datasource in tomcat global naming resource; Add following to server.xml (with your own password)
	<Resource auth="Container" driverClassName="org.postgresql.Driver" maxActive="20" maxIdle="10" maxWait="-1" name="jdbc/postgres" password="1234" type="javax.sql.DataSource" url="jdbc:postgresql://localhost:5432/ssase13" username="postgres"/>
* Add postgresql-jdbc.jar to your tomcat_installation/lib