BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `patients` (
                                         id            INTEGER
                                             constraint patients_pk
                                                 primary key autoincrement,
                                         name          TEXT,
                                         surname       TEXT,
                                         securityNum   INTEGER,
                                         phone         TEXT,
                                         adress        TEXT,
                                         complaint     TEXT,
                                         birthday      DATE,
                                         weight        DOUBLE,
                                         height        DOUBLE
);
INSERT INTO `patients` VALUES (1,'Dave','Rot',123456,'123/123-123', 'Chicago', 'Headache', 25301600000, 85,175);
INSERT INTO `patients` VALUES (2,'Dany','Boy',654321,'061/111-111', 'Florida', 'Paint in wrist', 112566400000, 75,180);
INSERT INTO `patients` VALUES (3,'Johnny','Sandman',321456,'061/222-123', 'Boston', 'Flesh wound', 585779200000, 83,175);
CREATE TABLE IF NOT EXISTS `appointments` (
                                          id            INTEGER
                                              constraint patients_pk
                                                  primary key autoincrement,
                                          date TIMESTAMP,
                                          patient INTEGER,
                                          FOREIGN KEY (patient) references patients(id)

);
INSERT INTO `appointments` VALUES (1,1568031300000,1);
INSERT INTO `appointments` VALUES (2,1563965460000,3);

CREATE TABLE IF NOT EXISTS `login` (
                                    username            TEXT,
                                    password            TEXT
);
INSERT INTO `login` VALUES ('Amer','123');
INSERT INTO `login` VALUES ('Doctor','password');
INSERT INTO `login` VALUES ('Prof','123');

COMMIT;
