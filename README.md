**Welcome to FDDemo**
===================


This is a simple application designed to demo, Kundera, jwt, and jersey.


[Cassandra db script](https://github.com/alejandrade/FDDemo/blob/master/cassandra.cql)


----------


#**Documentation**

###**Account Endpoints**
###GET: Validate User
```
http://dgui.gigamog.com/FirstDemo/account/validate?t=\${token}&p=\${password}
```
----------

| Key| Description|
|:----------:|:-------------:|
| ${token} |  unique id throughout api |
| ${password}|    assigned password   |


####Response body
```json
{  
"jwt": String "eyJhbGciOiJIUzI1NiJ9.eiczRnMDUifQ.MwJn-jb0jnBGSg"
}
```

##**API Endpoints**
###**Important!**
The following endpoints must have an authorization header as follows

```
Authorization:JWT "eyJhbGciOiJIUzI1NiJ9.eiczRnMDUifQ.MwJn-jb0jnBGSg"
``` 



----------


###GET: Listing Favorite Ice Creams
```
http://dgui.gigamog.com/FirstDemo/api/listing
```

####Response body


```json
{
   "iceCreams":Array
   [
      {
         "inToken":String"yi54",
         "flavor":String"Rum",
         "name":String"Janice"
      },
      {
         "inToken"String:"sd",
         "flavor"String:"Vanilla",
         "name":String"Jack"
      }
   ]
}
```
----------


###GET: Single person's favorite ice cream
```
http://dgui.gigamog.com/FirstDemo/api/get/${token}
```



----------

| Key| Description|
|:----------:|:-------------:|
| ${token} |  unique id throughout api |


####Response body


```json
{
   "inToken":String"yrew",
   "flavor":String"Rocky Road",
   "name":String"james"
}
```


----------
###POST: Upsert Ice cream favorites
```
http://dgui.gigamog.com/FirstDemo/api/upsert
```
this endpoint can be used to update or insert. If you would like to insert you should leave out the token.

----------
####Request body


```json
{
   "inToken":[optional] String"yrew",
   "flavor":String"Rocky Road",
   "name":String"james"
}
```


####Response body


```json
{
   "inToken":String"yrew",
   "flavor":String"Rocky Road",
   "name":String"james"
}
```



