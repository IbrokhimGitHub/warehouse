select  pr.id, pr.name, sum(ip.amount)as amount, sum(ip.price) price, count(pr.id) from input_product ip
join product pr on ip.product_id = pr.id
join input i on i.id = ip.input_id  where i.date=now() group by pr.id