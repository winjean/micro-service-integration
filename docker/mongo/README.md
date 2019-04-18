## http://www.runoob.com/mongodb/mongodb-create-database.html
* mongo
* show dbs
* db.winjean.insert({"winjean":"winjean"})
* db.winjean.find({}).pretty()
* db.winjean.update({"winjean":"winjean"},{"winjean":"winjean-1"})
* db.winjean.remove({"winjean":"winjean-1"})
* db.createUser({user: "winjean", pwd: "winjean", roles:[{ role: "readWrite",db: "winjean"}]})