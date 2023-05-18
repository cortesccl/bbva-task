# BBVA - Task

Se ha creado un CRUD  de Mercados, Instrumentos y Vwap.
Se inicializa la base de datos con datos demo recopilados en el fichero resources/data.sql

Una vez ejecutada la aplicación:
##### java -jar .\bbva-module-0.0.1-SNAPSHOT.jar
se lanza un job cada 40 segundos. (MarketUpdateScheduler.java)
Este job se encarga de realizar actualizaciones de profundidad de mercado para cada i instrumentos de m mercados.

Cada vez que se realiza un tick de mercado (o actualización) el sistema imprime
los valores correspondientes a esa actualización.
