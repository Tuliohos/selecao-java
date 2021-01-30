-- CREATE TABLE gas_station_data AS SELECT * FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;');

INSERT INTO REGION(name)
SELECT DISTINCT("REGIAO")
FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;');

INSERT INTO STATE(name, region_id)
SELECT DISTINCT(gtd."ESTADO"), reg.id
FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;') AS gtd
INNER JOIN region AS reg
ON gtd."REGIAO" = reg.name;

INSERT INTO COUNTY(name, state_id)
SELECT DISTINCT(gtd."MUNICIPIO"), sta.id
FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;') AS gtd
INNER JOIN state AS sta
ON gtd."ESTADO" = sta.name;

INSERT INTO PRODUCT(
	manufacturer,
	description,
	unit_measurement,
	creation_datetime,
	modification_datetime)
SELECT 
DISTINCT("BANDEIRA"),
	"PRODUTO",
	"UNIDADE_MEDIDA",
	NOW(),
	NOW(),
FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;');

INSERT INTO DISTRIBUTOR(description, cnpj, creation_datetime)
SELECT DISTINCT "REVENDA", "CNPJ", NOW()
FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;');

INSERT INTO COLLECTION(
	collection_date,
	purchase_price,
	sale_value,
	county_id,
	distributor_id,
	product_id)
SELECT CONVERT(parseDateTime(gtd."DATA_COLETA",'dd/MM/yyyy'), DATE),
	gtd."VALOR_COMPRA",
	gtd."VALOR_VENDA",
	cnt.id,
	dst.id,
	prod.id 
FROM CSVREAD('classpath:/2019-1_CA.csv', null, 'charset=UTF-8 fieldSeparator=;') AS gtd
INNER JOIN county AS cnt
ON gtd."MUNICIPIO" = cnt.name
INNER JOIN DISTRIBUTOR AS dst
ON gtd."BANDEIRA" = dst.description
INNER  JOIN product AS prod
ON gtd."PRODUTO" = prod.description
WHERE gtd."VALOR_COMPRA" IS NOT NULL
LIMIT 1000;