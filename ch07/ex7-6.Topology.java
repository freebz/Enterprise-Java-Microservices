// 예제 7-6 Topology

AdvertisementHandle handle = Topology.lookup().advertise("allevents");
...
handle.unadvertise();
