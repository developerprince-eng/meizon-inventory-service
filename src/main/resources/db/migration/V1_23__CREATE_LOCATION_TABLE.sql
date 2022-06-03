CREATE TABLE public.location
(
    location_id varchar(32) NOT NULL,
    name varchar(255) NOT NULL ,
    description text,
    location_type varchar(32),
    region varchar(16),
    address varchar(32),
    CONSTRAINT location_pkey PRIMARY KEY (location_id),
    CONSTRAINT location_location_type_fk
        FOREIGN KEY (location_type)
            REFERENCES public.location_type(type_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT location_region_fk
        FOREIGN KEY (region)
            REFERENCES public.region(region_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT location_address_fk
        FOREIGN KEY (address)
            REFERENCES public.address(address_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);