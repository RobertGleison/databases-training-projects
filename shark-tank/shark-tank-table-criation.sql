BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "shark_episodio" (
	"shark_id"	INTEGER,
	"numero_do_episodio"	INTEGER,
	PRIMARY KEY("numero_do_episodio","shark_id"),
	FOREIGN KEY("numero_do_episodio") REFERENCES "episodio"("numero_do_episodio"),
	FOREIGN KEY("shark_id") REFERENCES "shark"
);
CREATE TABLE IF NOT EXISTS "episodio" (
	"numero_do_episodio"	INTEGER,
	"temporada"	INTEGER,
	PRIMARY KEY("numero_do_episodio")
);
CREATE TABLE IF NOT EXISTS "projeto" (
	"projeto_id"	INTEGER,
	"nome"	TEXT,
	"localidade"	TEXT,
	"website"	TEXT,
	"valor_de_mercado"	NUMERIC,
	"categoria"	TEXT,
	"descricao"	TEXT,
	"numero_do_episodio"	INTEGER,
	PRIMARY KEY("projeto_id")
);
CREATE TABLE IF NOT EXISTS "shark" (
	"name"	TEXT,
	"Shark_id"	INTEGER,
	PRIMARY KEY("Shark_id")
);
CREATE TABLE IF NOT EXISTS "investimento" (
	"shark_id"	INTEGER,
	"projeto_id"	INTEGER,
	"valor_do_investimento"	NUMERIC,
	"porcentagem_vendida_do_projeto"	REAL,
	FOREIGN KEY("projeto_id") REFERENCES "projeto",
	PRIMARY KEY("shark_id","projeto_id"),
	FOREIGN KEY("shark_id") REFERENCES "shark"("Shark_id")
);
CREATE TABLE IF NOT EXISTS "empreendedor" (
	"empreendedor_id"	INTEGER,
	"nome"	TEXT,
	"projeto_id"	INTEGER,
	"genero"	TEXT,
	PRIMARY KEY("empreendedor_id")
);
INSERT INTO "shark_episodio" VALUES (1,1);
INSERT INTO "shark_episodio" VALUES (1,2);
INSERT INTO "shark_episodio" VALUES (1,16);
INSERT INTO "shark_episodio" VALUES (1,17);
INSERT INTO "shark_episodio" VALUES (1,26);
INSERT INTO "shark_episodio" VALUES (2,1);
INSERT INTO "shark_episodio" VALUES (2,2);
INSERT INTO "shark_episodio" VALUES (2,16);
INSERT INTO "shark_episodio" VALUES (2,17);
INSERT INTO "shark_episodio" VALUES (2,26);
INSERT INTO "shark_episodio" VALUES (3,1);
INSERT INTO "shark_episodio" VALUES (3,2);
INSERT INTO "shark_episodio" VALUES (3,16);
INSERT INTO "shark_episodio" VALUES (3,17);
INSERT INTO "shark_episodio" VALUES (3,26);
INSERT INTO "shark_episodio" VALUES (4,1);
INSERT INTO "shark_episodio" VALUES (4,2);
INSERT INTO "shark_episodio" VALUES (4,16);
INSERT INTO "shark_episodio" VALUES (4,17);
INSERT INTO "shark_episodio" VALUES (4,26);
INSERT INTO "shark_episodio" VALUES (5,1);
INSERT INTO "shark_episodio" VALUES (5,2);
INSERT INTO "shark_episodio" VALUES (5,16);
INSERT INTO "shark_episodio" VALUES (5,17);
INSERT INTO "shark_episodio" VALUES (6,26);
INSERT INTO "episodio" VALUES (1,1);
INSERT INTO "episodio" VALUES (2,1);
INSERT INTO "episodio" VALUES (16,2);
INSERT INTO "episodio" VALUES (17,2);
INSERT INTO "episodio" VALUES (26,3);
INSERT INTO "projeto" VALUES (1,'Ionic Ear','St.Paul, MN',NULL,666667,'Novelties','Bluetooth device implant for your ear.',1);
INSERT INTO "projeto" VALUES (2,'Mr. Tods Pie Factory','Somerset, NJ','http://whybake.com/',4600000,'Specialty Food','Retail and wholesale pie factory with two retail locations in New Jersey.',1);
INSERT INTO "projeto" VALUES (3,'Ava the Elephant','Atlanta, GA','http://www.avatheelephant.com/',333333,'Baby and Child Care','Ava the Elephant is a godsend for frazzled parents of young children everywhere. This talking medicine dispenser makes it easy to administer medicine to little ones by turning the experience more playful and by providing positive reinforcement.',1);
INSERT INTO "projeto" VALUES (69,'Toygaroo','Los Angeles, CA',NULL,1000000,'Toys and Games','A subscription toy service.',16);
INSERT INTO "projeto" VALUES (73,'Fitness Stride','Overland Park, KS','http://www.fitnessstride.com',933333,'Fitness Apparel and Accessories','Rubber bands that increase the resistance in your exercise activities.',17);
INSERT INTO "projeto" VALUES (110,'Liquid Money','Miami, FL','http://www.liquidmoney.com/',2000000,'Novelties','Liquid Money answers the question: what if I smelled like a million bucks, instead of just looking like it? Not literally though! That would be unfortunate. Liquid Money for Him is formulated to smell like success. The fragrance opens with a woodsy aroma that gives way to notes of citrus, rosemary, grass, and ocean breeze. The company was founded and is led by Pat McCarthy, a sales executive with experience at top enterprise software companies. Liquid Moneys website even asserts the product is used at Microsoft, salesforce, and SAP. Not to forget the ladies, Liquid Money is also available in a womens formulation. All products come in a box packed with real shredded US dollar bills, just to really drive home the point!',26);
INSERT INTO "projeto" VALUES (112,'You Smell Soap','Austin, TX','http://www.yousmellsoap.com/',275000,'Personal Care and Cosmetics','You Smell Soap is luxurious without taking itself too seriously. Made natural ingredients like shea butter and olive oil, You Smell Soap is fragrant yet gentle and soothing on the skin. Made with shea butter and olive oil, these bars create an unmatched soft and creamy lather. You Smell Soap is naturally fragrant yet gentle and soothing on your skin. Each bar comes wrapped in its signature "You Smell" packaging that is vintage-inspired and truly conversation-sparking. You Smell Soap is vegan and free of parabens, phthalates, and gluten.',26);
INSERT INTO "shark" VALUES ('Barbara Corcoran',1);
INSERT INTO "shark" VALUES ('Robert Herjavec',2);
INSERT INTO "shark" VALUES ('Kevin OLeary',3);
INSERT INTO "shark" VALUES ('Daymond John',4);
INSERT INTO "shark" VALUES ('Kevin Harrington',5);
INSERT INTO "shark" VALUES ('Mark Cuban',6);
INSERT INTO "investimento" VALUES (1,2,230000,10.0);
INSERT INTO "investimento" VALUES (4,2,230000,10.0);
INSERT INTO "investimento" VALUES (1,3,460000,15.0);
INSERT INTO "investimento" VALUES (3,69,100000,10.0);
INSERT INTO "investimento" VALUES (5,69,100000,10.0);
INSERT INTO "investimento" VALUES (2,112,55000,20.0);
INSERT INTO "empreendedor" VALUES (1,'Darrin Johnson',1,NULL);
INSERT INTO "empreendedor" VALUES (2,'Tod Wilson',2,NULL);
INSERT INTO "empreendedor" VALUES (3,'Tiffany Krumins',3,NULL);
INSERT INTO "empreendedor" VALUES (74,'Nikki Pope',69,NULL);
INSERT INTO "empreendedor" VALUES (81,'Stacy Erwin',73,NULL);
INSERT INTO "empreendedor" VALUES (119,'Pat McCarthy',110,NULL);
INSERT INTO "empreendedor" VALUES (123,'Steve Nakisher',112,NULL);
INSERT INTO "empreendedor" VALUES (124,'Shane Talbott',112,NULL);
COMMIT;
