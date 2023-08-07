```
// // The current database to use.
use("database-name");
db.getCollectionNames()

db.cats.find

db.cats.insertOne( {name: "Патюша", age: 2, breed: "загадка природы",desc: "Любит жаренные кабачки и гонять крышечку от бутылки по дому."} )
db.cats.insertOne({name: "Кремушек", breed: "Шотландская вислоухая",age: 5,desc: "С виду, вредный, но внутри – настоящий кремушек",color: "Темно-серый"})
db.cats.insertOne( {name: "Снежок",age: 2,breed: "Персидский", desc: "Любит спать. Не любит не спать "})
db.cats.insertMany( [{name: "Патюша", age: 2, breed: "загадка природы",desc: "Любит жаренные кабачки и гонять крышечку от бутылки по дому."}, {name: "Кремушек", breed: "Шотландская вислоухая",age: 5,desc: "С виду, вредный, но внутри – настоящий кремушек",color: "Темно-серый"}, {name: "Снежок",age: 2,breed: "Персидский", desc: "Любит спать. Не любит не спать "}] )

db.cats.deleteMany( {age: 7} )

db.cats.find( {age: 3} )
db.cats.find( {age: {$gte: 3}} )

db.cats.updateMany( {}, {$set: {localtion: "Москва"}} )
db.cats.updateMany( {_id: ObjectId("64d13e28ed4951faf98c9cd9")}, {$set: {toys: ["мышка","мячик"]}} )
```