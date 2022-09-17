Desafio FullStack

Construir uma aplicação RESTful para controle de estoque com no mínimo duas entidades, como por exemplo: Produto e Movimento Estoque.

Entidades:
	
	Produto:
		• Código -- OK!
		• Descrição -- OK!
		• Tipo Produto (Eletrônico, eletrodoméstico, móvel) -- OK!
		• Valor no Fornecedor -- OK!
		• Quantidade em estoque -- OK!

	Movimento Estoque:
		• Produto -- OK!
		• Tipo Movimentação (Entrada/Saída) -- OK!
		• Valor de venda -- OK!
		• Data de venda -- OK!
		• Quantidade movimentada -- OK!

Uso de Tecnologias, escolha conforme opções abaixo.

Tecnologias BackEnd:
	• Java Spring Boot -- OK!

Tecnologias FrontEnd:
	• Vue.js
	• Angular
	• React


O mínimo esperado da aplicação:
	• Boas práticas de desenvolvimento e clean code; -- OK!

	• CRUD - (Create, Read, Update, Delete) de produtos; -- OK!

	• Efetuar entrada e saída dos produtos no estoque. É importante validar o saldo ao efetuar uma saída do produto, caso não haja quantidade suficiente, deve ser retornado uma mensagem específica;

	• Consulta de produtos por tipo, com quantidade de saída e quantidade disponível;

	• Consulta de lucro por produto, exibindo a quantidade total de saída, e total do lucro (valor de venda – valor do fornecedor).

Não é obrigatório, mas se quiser surpreender:
	• Teste unitário.
	• Swagger

Observações:
	• Para facilitar a criação de uma aplicação utilizando Spring Boot, acesse o Spring Initializr(https://start.spring.io/).  -- OK!

	• Para o banco de dados, uma sugestão é utilizar o H2 (banco de dados em memória), mas fique à vontade para utilizar o processo que achar mais adequado. -- OK!
