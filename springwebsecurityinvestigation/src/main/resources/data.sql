insert into users (username, password, enabled) values('eve', '$2a$12$Gfth.g6qzNyP1PtGGNe5Guv695.m7hTrrCC0XpA7k/kcHyVE0UEq.', 1);
insert into users (username, password, enabled) values('fred', '$2a$12$Gfth.g6qzNyP1PtGGNe5Guv695.m7hTrrCC0XpA7k/kcHyVE0UEq.', 1);

insert into authorities (username, authority) values('eve', 'USER');
insert into authorities (username, authority) values('eve', 'ADMIN');
insert into authorities (username, authority) values('fred', 'USER');