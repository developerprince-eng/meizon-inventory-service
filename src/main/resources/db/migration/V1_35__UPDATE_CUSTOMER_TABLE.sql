ALTER TABLE public.customer
    ADD COLUMN region varchar(16);
ALTER TABLE public.customer
    ADD CONSTRAINT customer_region_fk
        FOREIGN KEY (region)
            REFERENCES public.region(region_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;