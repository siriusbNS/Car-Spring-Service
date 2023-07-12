create schema if not exists public;
create table if not exists public.CarBrand (
                                     brandId SERIAL,
                                     brandName varchar(30) not null,
                                     foundingDate DATE,
                                     PRIMARY KEY (brandId)
);
create table if not exists public.CarModel (
                                     modelId SERIAL PRIMARY KEY,
                                     modelName varchar(30) not null ,
                                     modelLength int not null,
                                     modelWidth int not null,
                                     bodyType varchar(30) not null,
                                     brandId_ INT,
                                     FOREIGN KEY (brandId_) references public.CarBrand(brandId)
                                         ON DELETE SET NULL
);