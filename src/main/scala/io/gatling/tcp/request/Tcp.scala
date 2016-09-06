package io.gatling.tcp.request

import io.gatling.core.session.Expression
import io.gatling.tcp.action.{ TcpDisconnectActionBuilder, TcpSendActionBuilder, TcpConnectActionBuilder }
import io.gatling.tcp.{ TextTcpMessage, ByteTcpMessage }

class Tcp(requestName: Expression[String]) {

  def connect() = new TcpConnectActionBuilder(requestName)

  def sendText(text: Expression[String]) = new TcpSendActionBuilder(requestName, text.map(TextTcpMessage))

  def sendBytes(bytes: Expression[Array[Byte]]) = new TcpSendActionBuilder(requestName, bytes.map(ByteTcpMessage))

  def disconnect() = new TcpDisconnectActionBuilder(requestName)

}
