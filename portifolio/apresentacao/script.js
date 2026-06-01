function enviarMensagem(event) {
  event.preventDefault();
  const nome = document.getElementById("nome").value;
  const mensagem = document.getElementById("mensagem").value;
  const telefone = "5511949944171";
  const texto = `Olá, meu nome é ${nome} e gostaria de entrar em contato. ${mensagem}`;
  const mensagemFormatada = encodeURIComponent(texto);
  const url = `https://wa.me/${telefone}/?=${mensagemFormatada}`;
  window.open(url, "_blank");
}
