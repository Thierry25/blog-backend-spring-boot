INSERT INTO users(id, email, name, password, username)
VALUES(1, 'thierry.marcelin@gmail.com', 'Thierry', 'PASSWORD20230211', 'thierry');

INSERT INTO users(id, email, name, password, username)
VALUES(2, 'admin@gmail.com', 'admin', 'PASSWORD20230212', 'admin');

INSERT INTO roles(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO roles(id, name) VALUES(2, 'ROLE_ADMIN');

INSERT INTO users_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES(2, 2);

-- username: thierry007 password: P@$$w0rd12345 -> latest user