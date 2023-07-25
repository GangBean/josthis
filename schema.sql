create table analyst (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), name varchar(255), primary key (id)) engine=InnoDB
;create table analyst_reports (analyst_id bigint not null, reports_id bigint not null, primary key (analyst_id, reports_id)) engine=InnoDB
;create table bad_stock_status_holder (status varchar(255) not null, primary key (status)) engine=InnoDB
;create table consensus (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), score decimal(19,2), type varchar(255), primary key (id)) engine=InnoDB
;create table institution (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), name varchar(255), primary key (id)) engine=InnoDB
;create table institution_analysts (institution_id bigint not null, analysts_id bigint not null, primary key (institution_id, analysts_id)) engine=InnoDB
;create table josthis_score (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), score decimal(19,2) default 0 not null, primary key (id)) engine=InnoDB
;create table report (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), content varchar(255), opinion_type integer, publishing_date datetime(6), target_price decimal(19,2), title varchar(255), type_id bigint, primary key (id)) engine=InnoDB
;create table reportable (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), primary key (id)) engine=InnoDB
;create table report_entity (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), primary key (id)) engine=InnoDB
;create table stock (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), dividend decimal(19,2) default 0 not null, eps decimal(19,2) default 0 not null, listed_volume decimal(19,2) default 0 not null, name varchar(255) not null, per decimal(19,2) default 0 not null, price decimal(19,2) default 0 not null, ticker_code varchar(14) not null, consensus_id bigint, josthis_score_id bigint, primary key (id)) engine=InnoDB
;create table stock_bad_status (stock_id bigint not null, bad_status_status varchar(255) not null, primary key (stock_id, bad_status_status)) engine=InnoDB
;create table stock_histories (stock_id bigint not null, histories_id bigint not null, primary key (stock_id, histories_id)) engine=InnoDB
;create table stock_history (id bigint not null auto_increment, created_at datetime(6), created_by varchar(255), last_modified_at datetime(6), last_modified_by varchar(255), primary key (id)) engine=InnoDB
;alter table analyst_reports add constraint UK_5dy49h91jgtno8bwsyn9otfxx unique (reports_id)
;alter table institution_analysts add constraint UK_1i4h6on57402gf6rcndteckpi unique (analysts_id)
;alter table stock add constraint UK_9fjf4aj1d5f2m6wi2uqmdan0a unique (name)
;alter table stock add constraint UK_5h6yv3p7n7nb49jmdg7sx29bd unique (ticker_code)
;alter table stock_bad_status add constraint UK_qcn6q11368cb1xhr8oteshu3d unique (bad_status_status)
;alter table stock_histories add constraint UK_ef7y7oc8s5h5mcedd47a28cdl unique (histories_id)
;alter table analyst_reports add constraint FKdhic439qwhv7nvxdtrr9rlo3o foreign key (reports_id) references report (id)
;alter table analyst_reports add constraint FKdhppk0b4sncadod31uglqhyw8 foreign key (analyst_id) references analyst (id)
;alter table institution_analysts add constraint FKnrdgrdgoe7n695fgg5q47wnb4 foreign key (analysts_id) references analyst (id)
;alter table institution_analysts add constraint FKhe765ogl100c9rbrju3gb8rq8 foreign key (institution_id) references institution (id)
;alter table report add constraint FK6j5im0c7kslaqm2to8q6t1xy0 foreign key (type_id) references reportable (id)
;alter table stock add constraint FKifvn2ek08tvw7edsh3jdlxo4t foreign key (consensus_id) references consensus (id)
;alter table stock add constraint FK9c826jvf9n5jorvu1jh3ye5js foreign key (josthis_score_id) references josthis_score (id)
;alter table stock_bad_status add constraint FKepkpubckfjcdn2sob4khe9g0p foreign key (bad_status_status) references bad_stock_status_holder (status)
;alter table stock_bad_status add constraint FK8c7h804kte09uy04hceh81dx1 foreign key (stock_id) references stock (id)
;alter table stock_histories add constraint FKk5v7xn3ljfjer67xf6u6n5exc foreign key (histories_id) references stock_history (id)
;alter table stock_histories add constraint FKgqolollvws7nwb8eav9ekyyg foreign key (stock_id) references stock (id)
;