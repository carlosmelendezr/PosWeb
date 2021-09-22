CREATE VIEW facturas_bolivares
AS 
SELECT 
factura.id,
factura.fecha,
factura.numero,
clientes.cedula,
clientes.razonsoc,
factura.caja,
factura.base*tasa_cambio.valor as base,
factura.impuesto*tasa_cambio.valor as impuesto,
factura.total*tasa_cambio.valor as total,
factura.descuento*tasa_cambio.valor as descuento,
tasa_cambio.valor as tasacambio
FROM factura 
LEFT JOIN tasa_cambio on factura.fecha = tasa_cambio.fecha 
LEFT JOIN clientes on factura.idcliente = clientes.id
WHERE imprime=1 