CREATE TABLE IF NOT EXISTS public.supplier (
       supplier_id varchar(32) NOT NULL,
       company_name varchar(255),
       city varchar(32),
       address varchar(32),
       contacts varchar(255),
       CONSTRAINT supplier_pkey PRIMARY KEY (supplier_id),
       CONSTRAINT supplier_city_fk
           FOREIGN KEY (city)
               REFERENCES public.city(city_id)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION,
       CONSTRAINT supplier_address_fk
           FOREIGN KEY (address)
               REFERENCES public.address(address_id)
               ON DELETE CASCADE
               ON UPDATE NO ACTION,
       CONSTRAINT supplier_contact_fk
           FOREIGN KEY (contacts)
               REFERENCES public.contact(contact_id)
               ON DELETE CASCADE
               ON UPDATE CASCADE
);