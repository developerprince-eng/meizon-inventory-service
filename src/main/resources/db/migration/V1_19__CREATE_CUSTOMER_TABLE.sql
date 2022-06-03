CREATE TABLE IF NOT EXISTS public.customer (
     customer_id varchar(255) NOT NULL,
     first_name varchar(255),
     last_name varchar(255),
     company_name varchar(255),
     city varchar(32),
     address varchar(32),
     contacts varchar(255),
     CONSTRAINT customer_pkey PRIMARY KEY (customer_id),
     CONSTRAINT customer_city_fk
         FOREIGN KEY (city)
             REFERENCES public.city(city_id)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION,
     CONSTRAINT customer_address_fk
         FOREIGN KEY (address)
             REFERENCES public.address(address_id)
             ON DELETE CASCADE
             ON UPDATE NO ACTION,
     CONSTRAINT customer_contact_fk
         FOREIGN KEY (contacts)
             REFERENCES public.contact(contact_id)
             ON DELETE CASCADE
             ON UPDATE CASCADE
);