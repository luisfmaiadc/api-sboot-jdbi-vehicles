UPDATE TbVeiculo
SET
    fabricante = :fabricante,
    modelo = :modelo,
    placa = :placa,
    ano_fabricacao = :anoFabricacao
WHERE id = :id