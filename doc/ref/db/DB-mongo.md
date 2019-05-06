## Authentication

```
$ mongo
> use admin
> db.createUser({user: 'root', pwd: 'PASSWORD', roles: ['dbOwner', 'root']})

$ mongo admin -u root -p
> use xxx
> db.createUser({user: 'dev', pwd: 'Dev.1234', roles: ['readWrite']})
> use xxx_test
> db.createUser({user: 'dev', pwd: 'Dev.1234', roles: ['readWrite']})
```

```
# /etc/mongod.conf
security:
  authorization: enabled

# then restart service
$ mongo admin -u root -p
> show dbs
> show users

$ mongo elxxxdbt:27017/xxx -u dev -p
> show collections
```
