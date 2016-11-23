# ch.bedag.vrk.rmi

What does this Java Application do?
-----------------------------------
The file watch application runs as a window service.
It monitors a specific file system folder X for changes (FILE-CREATE) ever X seconds.
Both this folder & the scanning time can be configured in the application properties file.

This folder X should only contain *docx templates and its corresponding *xml metadata file. If any pair changes are made to these documents a sysout is made and these files shall be added to a list.

The Spring boot's server is a configured tomcat 8.

Prerequisites
=============
You need to install / configure the following tools if you want to run this application:-

Tools
-------
* [JDK jdk1.6.0_26_x64 JRE]
* [Maven](http://maven.apache.org/) (the application is tested with Maven 3.2.1)
	* Configure Maven 3.3.3
* Eclipse IDE, for Eclipse Kempler R2
* [GIT Bash](https://github.com/git-for-windows/git/releases/download/v2.6.1.windows.1/Git-2.6.1-64-bit.exe)

Backend
-------
* Apache commons-daemon 1.0.1.5 binaries (This is optional as it has already being configured for this app)

## Developer information
	* Property files:
		- This project has 1 main property files which is situated in the project: 
			- src\main\resources\application-live.properties (contains global application configurations).

	* Configurations for Spring-boot aos-demo-file-watcher App (For #firstTimeSetup)
		- local folder to be watched should be configured in properties file

	* Build :: Go to project root directory and execute
		- mvn clean install
		
	* Startup Application
		* CD to mavens target directory.
		* java -jar aos-demo-file-watcher-0.1.jar

## Docs
Check out doc folder for sample files.

## Open points
- script has not being included to start service as procrun