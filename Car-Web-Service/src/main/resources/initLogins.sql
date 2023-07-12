CREATE table Person(
    id SERIAL,
    username varchar(100) NOT NULL,
    birth_date date not null,
    password varchar not null,
    role varchar(100) not null,
    brandid_ INT,
    FOREIGN KEY (brandid_) references public.carbrand(brandid)
        ON DELETE SET NULL
);