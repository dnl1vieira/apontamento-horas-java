CREATE TABLE TB_HOUR_POINT (
    id bigint(11) primary key,
    CUSTOMER varchar(255),
    HOUR varchar(50),
	PROJECT varchar(255),
    SERVICE varchar(255),
    DESCRIPTION varchar(255),
    MANAGER varchar(200),
    DATE datetime,
    CREATE_DATE datetime default now()
);