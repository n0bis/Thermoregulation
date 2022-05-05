//This file was generated from (Academic) UPPAAL 4.1.26-1 (rev. 7BCF30B7363A9518), February 2022
/*
*/
A[] not deadlock
/*
*/
E<> PropSub.reset
/*
*/
E<> PropPup.publish
/*
*/
E<> MQTTpublish.PingReq imply PropPup.PingReq
/*
*/
E<> MQTTsubscribe.PingReq imply PropSub.PingReq
/*
*/
[] (PropPup.PingReq -> <> PropPup.PingResp)
/*
*/
E<>(PropSub.PUB_ACK and x1 < 0.1)
/*
*/
E<> MQTTpublish.B9