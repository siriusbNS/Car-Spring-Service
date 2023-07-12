create table if not exists public.carEngine (
                                    engineId SERIAL,
                                    engineName varchar(30) not null,
                                    engineV int not null ,
                                    countOfCylinders int not null,
                                    engineHeight int not null,
                                    modelid_ INT,
                                    FOREIGN KEY (modelid_) references public.carModel(modelid)
                                        ON DELETE SET NULL
);