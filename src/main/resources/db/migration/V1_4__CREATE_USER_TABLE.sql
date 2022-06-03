CREATE TABLE IF NOT EXISTS public.user (
     email varchar(255),
     first_name varchar(255) ,
     last_name varchar(255) ,
     mobile varchar(32),
     employee_id VARCHAR(96),
     address varchar(32),
     location_id varchar(32),
     organisational_id bigint,
     CONSTRAINT user_pkey PRIMARY KEY (email, location_id, organisational_id),
      CONSTRAINT user_address_fk
          FOREIGN KEY (address)
              REFERENCES public.address(address_id)
              ON DELETE CASCADE
              ON UPDATE NO ACTION
);