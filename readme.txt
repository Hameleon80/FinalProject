Чтобы приложение успешно выполнялось необходимо:

- создать нового пользователя с именем airlines_admin и паролем adminpass
	
	CREATE USER 'airlines_admin'@'%' IDENTIFIED BY 'adminpass';
	
- дать ему все права на БД AirlinesDB

	GRANT ALL PRIVELEGES ON AirlinesDB.* TO airlines_admin@'%';