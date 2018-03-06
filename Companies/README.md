For using MongoDB in local machine

Install MongoDB

Set up the MongoDB environment. MongoDB requires a data directory to store all data. MongoDB’s default data directory path is \data\db. Before the first usage, you should create this folder. For more information visit https://docs.mongodb.com/getting-started/shell/installation/

In terminal window go to folder \MongoDB\Server\3.2\bin

Launch the server Mongo with command mongod

In another terminal window go to the same folder \MongoDB\Server\3.2\bin

Launch the MongoDB shell with command mongo

You can use command help to see available commands

Create user in the admin db

use admin
db.createUser(
{
    user: "root",
    pwd: "root",
    roles: [{role: "root", db: "admin"},
            {role: "userAdminAnyDatabase", db: "admin"}]
}
)
Create or switch to the db account

use companies
Create user in the account db

db.createUser( { user: "root", pwd: "root", roles: [{role: "dbAdmin", db: "companies"}] } )

Using MongoDB for Linux users (local machine install)

Install MongoDB using your distro package manager. For example, in Fedora: sudo dnf install mongodb-org
Start mongodb: sudo systemctl start mongod
Check status (should be "active"): sudo systemctl status mongod
If you want to start mongodb every time with system: sudo systemctl enable mongod
Run mongo shell in console with: mongo
Do steps 8-10 from section above