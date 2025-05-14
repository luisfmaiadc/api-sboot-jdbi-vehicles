UPDATE TbManutencao
SET
    descricao = :descricao,
    custo = :custo,
    ativa = :ativa
WHERE id = :id