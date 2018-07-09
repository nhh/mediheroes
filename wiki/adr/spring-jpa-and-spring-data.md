# Spring jpa entities and Spring data documents

We decided not to use the Cross Store of Spring because:

* Deprecation in Boot 2.0 + Several additional deps
* Having a Database managed referenced list of files/documents makes it easy to create a database
consistent dump/backup
