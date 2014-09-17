package com.puppetlabs.gatling.node_simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.concurrent.duration._

class PE33_ConsolePerfTestWithFeeder extends com.puppetlabs.gatling.runner.SimulationWithScenario {

  val nodeNames = csv("nodes.500.csv").circular

	val httpConf = httpConfig
			.baseURL("https://puppet-master:8140")
			.acceptHeader("pson, b64_zlib_yaml, yaml, raw")
			.connection("close")
			.userAgentHeader("Ruby")


	val headers_3 = Map(
			"Accept" -> """pson, b64_zlib_yaml, yaml, dot, raw""",
			"Content-Type" -> """application/x-www-form-urlencoded"""
	)

	val headers_110 = Map(
			"Accept" -> """pson, yaml""",
			"Content-Type" -> """text/pson"""
	)


	val chain_0 =
    feed(nodeNames)
		.exec(http("node")
					.get("/production/node/${node}")
					.queryParam("""transaction_uuid""", """218a4a79-d85f-43b8-8343-d8458b2db3b9""")
					.queryParam("""fail_on_404""", """true""")
			)
		.pause(346 milliseconds)
		.exec(http("filemeta plugins")
					.get("/production/file_metadatas/plugins")
					.queryParam("""checksum_type""", """md5""")
					.queryParam("""links""", """manage""")
					.queryParam("""recurse""", """true""")
					.queryParam("""ignore""", """.svn""")
					.queryParam("""ignore""", """CVS""")
					.queryParam("""ignore""", """.git""")
			)
		.pause(3)
		.exec(http("catalog")
					.post("/production/catalog/${node}")
					.headers(headers_3)
						.param("""facts_format""", """pson""")
						.param("""facts""", """%7B%22name%22%3A%22${node}%22%2C%22values%22%3A%7B%22architecture%22%3A%22x86_64%22%2C%22augeasversion%22%3A%221.1.0%22%2C%22kernel%22%3A%22Linux%22%2C%22blockdevice_sda_size%22%3A%221000171331584%22%2C%22blockdevice_sda_vendor%22%3A%22HP%22%2C%22blockdevice_sda_model%22%3A%22LOGICAL+VOLUME%22%2C%22blockdevice_sdb_size%22%3A%22300035497984%22%2C%22blockdevice_sdb_vendor%22%3A%22HP%22%2C%22blockdevice_sdb_model%22%3A%22LOGICAL+VOLUME%22%2C%22blockdevices%22%3A%22sda%2Csdb%22%2C%22domain%22%3A%22performance.delivery.puppetlabs.net%22%2C%22macaddress%22%3A%2284%3A34%3A97%3A11%3AD1%3A38%22%2C%22osfamily%22%3A%22RedHat%22%2C%22operatingsystem%22%3A%22CentOS%22%2C%22facterversion%22%3A%221.7.5%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22fqdn%22%3A%22${node}.performance.delivery.puppetlabs.net%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22hostname%22%3A%22${node}%22%2C%22id%22%3A%22root%22%2C%22interfaces%22%3A%22eth0%2Ceth1%2Clo%2Cvboxnet0%2Cvboxnet1%22%2C%22ipaddress_eth0%22%3A%2210.16.150.32%22%2C%22macaddress_eth0%22%3A%2284%3A34%3A97%3A11%3AD1%3A38%22%2C%22netmask_eth0%22%3A%22255.255.255.0%22%2C%22mtu_eth0%22%3A%221500%22%2C%22macaddress_eth1%22%3A%2284%3A34%3A97%3A11%3AD1%3A39%22%2C%22mtu_eth1%22%3A%221500%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22mtu_lo%22%3A%2216436%22%2C%22ipaddress_vboxnet0%22%3A%22192.168.33.1%22%2C%22macaddress_vboxnet0%22%3A%220A%3A00%3A27%3A00%3A00%3A00%22%2C%22netmask_vboxnet0%22%3A%22255.255.255.0%22%2C%22mtu_vboxnet0%22%3A%221500%22%2C%22ipaddress_vboxnet1%22%3A%22172.16.1.1%22%2C%22macaddress_vboxnet1%22%3A%220A%3A00%3A27%3A00%3A00%3A01%22%2C%22netmask_vboxnet1%22%3A%22255.255.255.0%22%2C%22mtu_vboxnet1%22%3A%221500%22%2C%22ipaddress%22%3A%2210.16.150.32%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22kernelrelease%22%3A%222.6.32-431.17.1.el6.x86_64%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22bios_vendor%22%3A%22HP%22%2C%22bios_version%22%3A%22P80%22%2C%22bios_release_date%22%3A%2209%2F01%2F2013%22%2C%22manufacturer%22%3A%22HP%22%2C%22productname%22%3A%22ProLiant+DL320e+Gen8+v2%22%2C%22serialnumber%22%3A%22USE346L3KJ%22%2C%22uuid%22%3A%2233323237-3531-5355-4533-34364C334B4A%22%2C%22type%22%3A%22Rack+Mount+Chassis%22%2C%22memorysize%22%3A%227.52+GB%22%2C%22memoryfree%22%3A%226.80+GB%22%2C%22swapsize%22%3A%22999.99+MB%22%2C%22swapfree%22%3A%22993.15+MB%22%2C%22swapsize_mb%22%3A%22999.99%22%2C%22swapfree_mb%22%3A%22993.15%22%2C%22memorysize_mb%22%3A%227703.16%22%2C%22memoryfree_mb%22%3A%226966.63%22%2C%22memorytotal%22%3A%227.52+GB%22%2C%22netmask%22%3A%22255.255.255.0%22%2C%22network_eth0%22%3A%2210.16.150.0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22network_vboxnet0%22%3A%22192.168.33.0%22%2C%22network_vboxnet1%22%3A%22172.16.1.0%22%2C%22operatingsystemmajrelease%22%3A%226%22%2C%22operatingsystemrelease%22%3A%226.4%22%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%22%2C%22physicalprocessorcount%22%3A%221%22%2C%22processor0%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor1%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor2%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor3%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor4%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor5%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor6%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processor7%22%3A%22Intel%28R%29+Xeon%28R%29+CPU+E3-1280+v3+%40+3.60GHz%22%2C%22processorcount%22%3A%228%22%2C%22ps%22%3A%22ps+-ef%22%2C%22puppetversion%22%3A%223.6.2+%28Puppet+Enterprise+3.3.0-rc2-367-gfc8a68c%29%22%2C%22rubysitedir%22%3A%22%2Fopt%2Fpuppet%2Flib%2Fruby%2Fsite_ruby%2F1.9.1%22%2C%22rubyversion%22%3A%221.9.3%22%2C%22selinux%22%3A%22false%22%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBAMGv2VgUwzekrK%2B1u1FG4XOT7iSScBgmoRXs%2BiAECUSgEig78bXgJGIUy0Ba9D5uMgXrJouhogYGAt4P%2FGFjpPTLbbNAnzoz4MXycweXTiCY59E2zZEHyuXnHFFsON8rrDng0E8clIKlSMZZ45OjbW6ZcFPFgfgbxe9ldGMOnmjrAAAAFQDyoU%2FeLflp2Kdg2nUWZPiRljjmlwAAAIAlzPgKnDqRA92Y%2FWntIq0DLx6YsfUfFKtfdr4U5E9CCEw3oWKHbZLMkpDRmbQOsDLO5jVHmnK8RoFkqIGJxfNGoPms6xkYx5TVSl5sU6qj0MSTc7b52buJwYEDIc8Y%2BwWRhvvP7vMAl7NTKLxZrL3m9%2FKgBwqUTqus7x%2BB5wKPKgAAAIEAkwwH5WTqDmki6R7Q5HIF1XyS949nLCuO2tmNR33MmBZM6Azl%2BR2j0wP9dK2dFfLnvafKk1YyfavCCQ0sgKonwUm%2BAcwK6kLd%2F6Y7I94wKf7WLEv4sYo8z3Zvhr370PIBDSUFK5zexqPL1DsiCqL8LbIqTAtxHcM0WbyYO2i9kaI%3D%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+582720876da840cf099f281d24dd653614238fd5%5CnSSHFP+2+2+5e31d5afc4bd904c7dffd785bef1c5d7b47213069dab27a4ac8c3d7e882a1aa1%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEAw3ZJbdOspZKT0ilXpiBCTxnkrO2u%2F7MWGbjmt5PML8o5SHzEQ0I99NAA5Hu1rk6W21HfOQ0mLvRCSv8wNA3g6uDueOGsUTQbYohGKsoT4zZvDRXp0e6e208tCwyivwzPVPS%2FQgJ2HGh4vQNbkuErncqWX8oJL%2BsALODbfCJ5%2FJMsnCTgVVZ1KC01YAjdF%2FcYnOnuOnZPslZGTdODnLUnwYRXNgFbGJy47C7zEdVpB5rz2EDyb%2BeDlcDmHpdZt03sno5AZSWC29PG8lDjUrMA8xd3gSqJWckAZMaRbFZ%2FObFYL7m7hL6m8ObRakvf0%2BRjoONr8ozKuqUDlYIWl6B%2B5w%3D%3D%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+bbf04873ad2da82eeef5dff9cf21f64d12220aab%5CnSSHFP+1+2+f53b73f56b30f3ea4a6f9a28a08923c9aa57d195fe38399f254dac26b6e2c1dd%22%2C%22timezone%22%3A%22EDT%22%2C%22uniqueid%22%3A%22100a2096%22%2C%22uptime%22%3A%228+days%22%2C%22uptime_days%22%3A%228%22%2C%22uptime_hours%22%3A%22192%22%2C%22uptime_seconds%22%3A%22691656%22%2C%22virtual%22%3A%22physical%22%2C%22is_virtual%22%3A%22false%22%2C%22concat_basedir%22%3A%22%2Fvar%2Fopt%2Flib%2Fpe-puppet%2Fconcat%22%2C%22custom_auth_conf%22%3A%22true%22%2C%22ip6tables_version%22%3A%221.4.7%22%2C%22iptables_version%22%3A%221.4.7%22%2C%22pe_build%22%3A%223.3.0-rc2-367-gfc8a68c%22%2C%22pe_postgres_default_version%22%3A%228.4%22%2C%22pe_puppetdb_server_status%22%3A%22configured%22%2C%22pe_version%22%3A%223.3.0%22%2C%22is_pe%22%3A%22true%22%2C%22pe_major_version%22%3A%223%22%2C%22pe_minor_version%22%3A%223%22%2C%22pe_patch_version%22%3A%220%22%2C%22platform_tag%22%3A%22el-6-x86_64%22%2C%22postgres_default_version%22%3A%228.4%22%2C%22puppet_vardir%22%3A%22%2Fvar%2Fopt%2Flib%2Fpe-puppet%22%2C%22root_home%22%3A%22%2Froot%22%2C%22staging_http_get%22%3A%22curl%22%2C%22clientcert%22%3A%22${node}%22%2C%22clientversion%22%3A%223.6.2+%28Puppet+Enterprise+3.3.0-rc2-367-gfc8a68c%29%22%2C%22clientnoop%22%3A%22false%22%7D%2C%22timestamp%22%3A%222014-06-26+18%3A41%3A03+-0400%22%2C%22expiration%22%3A%222014-06-26T19%3A11%3A03.482812097-04%3A00%22%7D""")
						.param("""transaction_uuid""", """218a4a79-d85f-43b8-8343-d8458b2db3b9""")
						.param("""fail_on_404""", """true""")
			)
		.pause(2)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(295 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(200 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero1/catalog-zero1-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(309 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero2/catalog-zero2-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(307 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl23.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(172 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(280 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero3/catalog-zero3-impl74.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(211 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero4/catalog-zero4-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(374 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl22.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(194 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl42.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(163 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl43.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(207 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero5/catalog-zero5-impl52.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(319 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero6/catalog-zero6-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(224 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero6/catalog-zero6-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(291 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(198 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl34.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(269 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl82.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(198 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(151 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero7/catalog-zero7-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(191 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl11.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(369 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(219 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(283 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero8/catalog-zero8-impl81.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(324 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl63.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(609 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(207 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero9/catalog-zero9-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(196 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(196 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl22.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(216 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl41.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(248 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(161 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(208 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero10/catalog-zero10-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(205 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl11.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(168 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl21.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(179 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(262 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl62.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(195 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero11/catalog-zero11-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(231 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(301 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl82.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(148 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(141 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero12/catalog-zero12-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(288 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(194 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(199 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl74.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(162 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(193 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(156 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero13/catalog-zero13-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(334 milliseconds)
	val chain_1 =
		exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero14/catalog-zero14-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(374 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl62.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(203 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(157 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero15/catalog-zero15-impl73.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(300 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(287 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(153 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero16/catalog-zero16-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(191 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero17/catalog-zero17-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(326 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero17/catalog-zero17-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(205 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl14.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(188 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl23.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(151 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(194 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(255 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero18/catalog-zero18-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(201 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl12.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(243 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl31.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(204 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl53.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(166 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(258 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(181 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero19/catalog-zero19-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(242 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(204 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl42.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(244 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(146 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero20/catalog-zero20-impl73.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(262 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl32.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(202 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl41.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(249 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl82.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(149 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(182 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero21/catalog-zero21-impl87.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(238 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero22/catalog-zero22-impl34.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(211 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero22/catalog-zero22-impl52.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(305 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero23/catalog-zero23-impl21.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(258 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero23/catalog-zero23-impl62.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(331 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero24/catalog-zero24-impl52.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(234 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero24/catalog-zero24-impl83.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(212 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(162 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl22.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(189 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl24.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(248 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero25/catalog-zero25-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(362 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl54.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(187 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl64.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(197 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero26/catalog-zero26-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(253 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(161 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl41.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(241 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero27/catalog-zero27-impl63.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(258 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl13.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(204 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl33.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(207 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl51.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(231 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl72.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(167 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero28/catalog-zero28-impl84.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(351 milliseconds)
	val chain_2 =
		exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero29/catalog-zero29-impl71.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(186 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero29/catalog-zero29-impl86.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(266 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero30/catalog-zero30-impl34.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(286 milliseconds)
		.exec(http("filemeta")
					.get("/production/file_metadata/modules/catalog-zero30/catalog-zero30-impl85.txt")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(304 milliseconds)
		.exec(http("filemeta mco plugins")
					.get("/production/file_metadatas/modules/pe_mcollective/plugins")
					.queryParam("""checksum_type""", """md5""")
					.queryParam("""links""", """manage""")
					.queryParam("""recurse""", """true""")
			)
		.pause(338 milliseconds)
		.exec(http("filemeta repo GPG-KEY-puppetlabs")
					.get("/production/file_metadata/modules/pe_repo/GPG-KEY-puppetlabs")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(334 milliseconds)
		.exec(http("filemeta puppetdb routes")
					.get("/production/file_metadata/modules/pe_puppetdb/routes.yaml")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(2)
		.exec(http("filemeta accounts shell bashrc")
					.get("/production/file_metadata/modules/pe_accounts/shell/bashrc")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(183 milliseconds)
		.exec(http("filemeta accounts shell bash_profile")
					.get("/production/file_metadata/modules/pe_accounts/shell/bash_profile")
					.queryParam("""source_permissions""", """use""")
					.queryParam("""links""", """manage""")
			)
		.pause(2)
    		.exec(http("report")
					.put("/production/report/${node}")
					.headers(headers_110)
					.body(scala.io.Source.fromFile("./user-files/request-bodies/PE33_ConsolePerfTestWithFeeder_request_110.txt").mkString)
			)

	val scn = scenario("Scenario Name")
		.exec(
			chain_0,			chain_1,			chain_2		)

	setUp(scn.users(1).protocolConfig(httpConf))
}
