ALTER TABLE public.product
DROP CONSTRAINT product_rate_fk;
ALTER TABLE public.product
DROP CONSTRAINT product_category_fk;
ALTER TABLE public.product
DROP COLUMN categories;
ALTER TABLE public.product
DROP COLUMN rates;
ALTER TABLE public.product
ADD COLUMN category VARCHAR(32);
ALTER TABLE public.product
ADD CONSTRAINT product_category_fk
    FOREIGN KEY (category)
        REFERENCES public.category(category_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION;

