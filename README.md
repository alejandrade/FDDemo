Welcome to FDDemo
===================


This is a simple application designed to demo, Kundera, jwt, and jersey.

----------


Documentation
-------------
####GET: Validate User
```
http://dgui.gigamog.com/FirstDemo/account/validate?t=\${token}&p=\${password}
```
----------
${token}|primary id through out api
----------
${password}|assigned password

| Key| Description|
|:----------:|:-------------:|
| ${token} |  unique id throughout api |
| ${password}|    assigned password   |


####Response body
```
{  
"jwt": String "eyJhbGciOiJIUzI1NiJ9.eiczRnMDUifQ.MwJn-jb0jnBGSg"
}
```
####Important
The following endpoints must have an authorization header as follows



```
Authorization:JWT "eyJhbGciOiJIUzI1NiJ9.eiczRnMDUifQ.MwJn-jb0jnBGSg"
``` 


----------


####GET: Listing Favorite Ice Creams
```
http://dgui.gigamog.com/FirstDemo/api/listing
```
| Important information |
|:----------:|
| Authorization header in mandatory | 

####Response body



{  
"jwt": String "eyJhbGciOiJIUzI1NiJ9.eiczRnMDUifQ.MwJn-jb0jnBGSg"
}
```
