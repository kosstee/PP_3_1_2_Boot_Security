INSERT INTO users (username, name, password, enabled)
values ('admin', 'Ivan', '$2a$13$jbQzawgZ7v.DniYbrLvyWOQ17Umm9ZwQBoV4lTSBrG585/Zf3BLLG', 1); --pass=admin

INSERT INTO users (username, name, password, enabled)
values ('user', 'Tom', '$2a$13$b0q90wn4drDhTef0L1/gL.V6YqO2xWehOPPlEya4947KlReDCedie', 1); --pass=user

INSERT INTO users (username, name, password, enabled)
values ('admin-user', 'Peter', '$2a$13$m44afEohlJC.y1CL6JhM6u.uURsKA7BSfwG0u4VRKMGrW7CpWPvua', 1); --pass=admin-user

INSERT INTO roles (role) values ('ROLE_ADMIN');
INSERT INTO roles (role) values ('ROLE_USER');

INSERT INTO users_roles (user_id, role_id) values (1, 1);
INSERT INTO users_roles (user_id, role_id) values (2, 2);
INSERT INTO users_roles (user_id, role_id) values (3, 1);
INSERT INTO users_roles (user_id, role_id) values (3, 2);
