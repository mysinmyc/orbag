insert into person(id,name) values(-1, 'yoda');
insert into person(id,name) values(-2, 'frodo');

insert into server_group(id,name) values(-1,'group1');
insert into server_group(id,name) values(-2,'group2');
insert into server_group(id,name) values(-3,'admin');

insert into server(id,name,server_group_id,address,owner_id,productive_stage) values(-1,'server1a',-1,'server1a',-2,'production');
insert into server(id,name,server_group_id,address,owner_id,productive_stage) values(-2,'server1b',-1,'server1b',-2,'production');
insert into server(id,name,server_group_id,address,owner_id,productive_stage) values(-3,'server2a',-2,'server2a',-2,'test');
insert into server(id,name,server_group_id,address,owner_id,productive_stage) values(-4,'server2b',-2,'server2b',-2,'test');
insert into server(id,name,server_group_id,address,owner_id,productive_stage) values(-6,'admin',-1,'localhost',-1,'production');