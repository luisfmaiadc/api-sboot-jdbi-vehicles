INSERT INTO TbManutencao (
    id_veiculo,
    descricao,
    custo,
    data,
    ativa
) VALUES (
    :idVeiculo,
    :descricao,
    :custo,
    :dataManutencao,
    true
);