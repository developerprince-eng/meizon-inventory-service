ALTER TABLE public.rate
ADD COLUMN product varchar(16);
ALTER TABLE public.rate
ADD CONSTRAINT product_rate_fk
    FOREIGN KEY (product)
        REFERENCES public.product(product_sku)
        ON DELETE CASCADE
        ON UPDATE CASCADE;