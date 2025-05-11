SELECT id,
    fabricante,
    modelo,
    placa,
    ano_fabricacao AS anoFabricacao
FROM TbVeiculo
WHERE id = :id