BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "patients" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT,
	"surname"	TEXT,
	"securityNum"	INTEGER,
	"phone"	TEXT,
	"adress"	TEXT,
	"complaint"	TEXT,
	"birthday"	DATE,
	"weight"	DOUBLE,
	"height"	DOUBLE
);
CREATE TABLE IF NOT EXISTS "appointments" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"appoDate"	TIMESTAMP,
	"patient"	INTEGER,
	FOREIGN KEY("patient") REFERENCES "patients"("id")
);
CREATE TABLE IF NOT EXISTS "login" (
	"username"	TEXT,
	"password"	TEXT
);
INSERT INTO "patients" ("id","name","surname","securityNum","phone","adress","complaint","birthday","weight","height") VALUES (1,'Davelot','Rot',123456,'033/456/789','Chicago','Headache',25225200000,85.0,175.0);
INSERT INTO "patients" ("id","name","surname","securityNum","phone","adress","complaint","birthday","weight","height") VALUES (2,'Dany','Boyi',654321,'123-123-456','New Orlean','Pain in wrist',112489200000,751.0,180.0);
INSERT INTO "patients" ("id","name","surname","securityNum","phone","adress","complaint","birthday","weight","height") VALUES (4,'Johny','Sans',614331,'456 321 456','Minesot','Stomacache',1372111200000,431.0,123.0);
INSERT INTO "patients" ("id","name","surname","securityNum","phone","adress","complaint","birthday","weight","height") VALUES (5,'Jim','Franklin',783121,'456.456.456','New Orleans','Cut',1590962400000,85.0,176.0);
INSERT INTO "appointments" ("id","appoDate","patient") VALUES (1,1568031300000,1);
INSERT INTO "appointments" ("id","appoDate","patient") VALUES (2,1631695200000,4);
INSERT INTO "appointments" ("id","appoDate","patient") VALUES (3,1593083700000,2);
INSERT INTO "login" ("username","password") VALUES ('Amer','123');
INSERT INTO "login" ("username","password") VALUES ('Doctor','password');
INSERT INTO "login" ("username","password") VALUES ('Prof','123');
COMMIT;
