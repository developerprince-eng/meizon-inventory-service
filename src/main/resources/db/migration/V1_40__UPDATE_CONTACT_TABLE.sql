ALTER TABLE public.contact
ADD COLUMN customer varchar(255);
ALTER TABLE public.contact
ADD CONSTRAINT contact_customer_fk
    FOREIGN KEY (customer)
        REFERENCES public.customer(customer_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;