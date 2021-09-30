CREATE VIEW facturas_dolares_v2
AS 
SELECT 
factura.id,
factura.fecha,
factura.numero,
clientes.cedula,
clientes.razonsoc,
factura.caja,
factura.base,
factura.impuesto,
factura.total,
factura.descuento,
tasa_cambio.valor as tasacambio
FROM factura 
LEFT JOIN tasa_cambio on factura.idtasa = tasa_cambio.id 
LEFT JOIN clientes on factura.idcliente = clientes.id
WHERE imprime=0 AND pagada=1
