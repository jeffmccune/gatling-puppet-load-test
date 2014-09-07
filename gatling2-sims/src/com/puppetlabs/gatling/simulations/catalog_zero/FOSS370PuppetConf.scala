package com.puppetlabs.gatling.simulations.catalog_zero

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.core.structure.{ChainBuilder}

class FOSS370PuppetConf extends Simulation {

	val httpProtocol = http
		.baseURL("https://puppet-master:8140")
		.disableFollowRedirect
		.disableAutoReferer
		.acceptHeader("""pson, yaml, b64_zlib_yaml, raw""")

	val headers_3 = Map("""Accept""" -> """pson, b64_zlib_yaml, dot, yaml, raw""")

	val headers_108 = Map(
		"""Accept""" -> """pson, yaml""",
		"""Content-Type""" -> """text/pson""")

    val uri1 = """https://puppet-master:8140/production"""

	val chain_0 = exec(http("node")
			.get("""/production/node/puppet-agent?fail_on_404=true&transaction_uuid=d1d0fd05-7247-4b0f-ba9c-540593b13f85"""))
		.exec(http("filemeta plugin facts")
			.get("""/production/file_metadatas/pluginfacts?recurse=true&checksum_type=md5&links=manage&ignore=.svn&ignore=CVS&ignore=.git"""))
		.exec(http("filemeta plugins")
			.get("""/production/file_metadatas/plugins?recurse=true&checksum_type=md5&links=manage&ignore=.svn&ignore=CVS&ignore=.git"""))
		.pause(344 milliseconds)
		.exec(http("catalog")
			.post("""/production/catalog/puppet-agent""")
			.headers(headers_3)
			.formParam("""fail_on_404""", """true""")
			.formParam("""transaction_uuid""", """d1d0fd05-7247-4b0f-ba9c-540593b13f85""")
			.formParam("""facts""", """%7B%22name%22%3A%22puppet-agent%22%2C%22timestamp%22%3A%22Wed+Sep+03+22%3A05%3A17+-0700+2014%22%2C%22expiration%22%3A%222014-09-03T22%3A35%3A17.586621000-07%3A00%22%2C%22values%22%3A%7B%22selinux_enforced%22%3A%22true%22%2C%22processor0%22%3A%22Intel%28R%29+Core%28TM%29+i7-4850HQ+CPU+%40+2.30GHz%22%2C%22serialnumber%22%3A%22VMware-56+4d+05+2a+46+f1+99+07-c0+fd+54+ba+63+b3+b0+13%22%2C%22memoryfree%22%3A%22258.83+MB%22%2C%22facterversion%22%3A%222.2.0%22%2C%22operatingsystemrelease%22%3A%226.4%22%2C%22ipaddress_eth0%22%3A%22192.168.7.130%22%2C%22boardserialnumber%22%3A%22None%22%2C%22path%22%3A%22%2Fusr%2Flocal%2Fsbin%3A%2Fusr%2Flocal%2Fbin%3A%2Fsbin%3A%2Fbin%3A%2Fusr%2Fsbin%3A%2Fusr%2Fbin%3A%2Froot%2Fbin%22%2C%22clientversion%22%3A%223.6.2%22%2C%22netmask_lo%22%3A%22255.0.0.0%22%2C%22manufacturer%22%3A%22VMware%2C+Inc.%22%2C%22network_eth0%22%3A%22192.168.7.0%22%2C%22osfamily%22%3A%22RedHat%22%2C%22fqdn%22%3A%22puppet-agent%22%2C%22sshfp_rsa%22%3A%22SSHFP+1+1+0f0d05587ddd7d23850370c7724919bec5974bfe%5CnSSHFP+1+2+d496c6b36866dfc13fe728cd72ff5716d27b7ce00d6ec190b28c9c08ac2b7692%22%2C%22rubysitedir%22%3A%22%2Fusr%2Flib%2Fruby%2Fsite_ruby%2F1.8%22%2C%22timezone%22%3A%22PDT%22%2C%22memorysize_mb%22%3A%22482.84%22%2C%22hardwareisa%22%3A%22x86_64%22%2C%22uptime_seconds%22%3A%2235738%22%2C%22productname%22%3A%22VMware+Virtual+Platform%22%2C%22uptime_days%22%3A%220%22%2C%22blockdevices%22%3A%22sda%2Csr0%22%2C%22network_lo%22%3A%22127.0.0.0%22%2C%22uptime%22%3A%229%3A55+hours%22%2C%22rubyversion%22%3A%221.8.7%22%2C%22processors%22%3A%22modelsIntel%28R%29+Core%28TM%29+i7-4850HQ+CPU+%40+2.30GHzcount1physicalcount1%22%2C%22selinux%22%3A%22true%22%2C%22selinux_config_mode%22%3A%22enforcing%22%2C%22netmask_eth0%22%3A%22255.255.255.0%22%2C%22uniqueid%22%3A%22007f0100%22%2C%22bios_version%22%3A%226.00%22%2C%22processorcount%22%3A%221%22%2C%22virtual%22%3A%22vmware%22%2C%22blockdevice_sda_size%22%3A%2221474836480%22%2C%22uptime_hours%22%3A%229%22%2C%22filesystems%22%3A%22ext4%2Ciso9660%22%2C%22macaddress%22%3A%2200%3A0C%3A29%3AB3%3AB0%3A13%22%2C%22clientcert%22%3A%22puppet-agent%22%2C%22bios_vendor%22%3A%22Phoenix+Technologies+LTD%22%2C%22puppetversion%22%3A%223.6.2%22%2C%22sshdsakey%22%3A%22AAAAB3NzaC1kc3MAAACBAJjKvA06mb9LyX8kg5QZ5YHx0COBBMzyrdM4ZDDF0reTYcnN2FKvBc%2F2goY%2FX1aHNxaU3MdPtUeq1jj4x%2F1nAQbFH3i7iehEAg7cqyq7U1ZJb6cDmcOm8MYp33bQf%2FkZesTMCa0sy6gPk9eC7V5PLxVCM1upfqGv1jRm96Q%2BtzIjAAAAFQDhBT0mCloYdW9KHIhW00YgiSppqQAAAIBG1VfwL%2BHA6lfnFVkoVs2954C1e3ZLiw8FCNUW7%2BuGTifYQlhr5%2FvhoNWK%2B1kgn0nN0NsXvq0j08Dw7GOx5mezlbj9ZswTGVl4NemmhE1l8Vy1NoJS5EA7l6%2FrxTBPzWsRkgNPZjl7ETECA%2BlT%2FDdTP3SsQhFeq52XdP9pLcRawwAAAIA6%2BEEGDB9nEYE1SyHu78XIBjKCuA8A6IlRztlQzjfkM49bHJuFqTAtGhWQEygVTo80U6pXkfXGYwOra46ROTflEYEVPPP6aUfjnWiZEEkDyYkkM2C0%2FjROFK5at58RhTDDLsF9XeTLnUzXoUtJhX5OT2zv%2BgwzIoSydRsNKN%2FnEA%3D%3D%22%2C%22gid%22%3A%22root%22%2C%22blockdevice_sr0_size%22%3A%221073741312%22%2C%22swapsize%22%3A%22991.99+MB%22%2C%22mtu_lo%22%3A%2216436%22%2C%22architecture%22%3A%22x86_64%22%2C%22hostname%22%3A%22jb-jvmpmaster-3%22%2C%22kernelrelease%22%3A%222.6.32-358.el6.x86_64%22%2C%22is_virtual%22%3A%22true%22%2C%22selinux_policyversion%22%3A%2224%22%2C%22boardproductname%22%3A%22440BX+Desktop+Reference+Platform%22%2C%22os%22%3A%22familyRedHatnameCentOSreleaseminor4major6full6.4%22%2C%22swapfree_mb%22%3A%22991.67%22%2C%22memoryfree_mb%22%3A%22258.83%22%2C%22macaddress_eth0%22%3A%2200%3A0C%3A29%3AB3%3AB0%3A13%22%2C%22clientnoop%22%3A%22false%22%2C%22type%22%3A%22Other%22%2C%22domain%22%3A%22local.vm%22%2C%22hardwaremodel%22%3A%22x86_64%22%2C%22physicalprocessorcount%22%3A%221%22%2C%22ps%22%3A%22ps+-ef%22%2C%22kernelversion%22%3A%222.6.32%22%2C%22netmask%22%3A%22255.255.255.0%22%2C%22selinux_current_mode%22%3A%22enforcing%22%2C%22sshfp_dsa%22%3A%22SSHFP+2+1+b76d8c9b9d903981a3f85db2e399eda81d036027%5CnSSHFP+2+2+58beafea7b4f1802509dbc442138395201d52b49850bd3b86458a3724b5f648e%22%2C%22blockdevice_sda_vendor%22%3A%22VMware%2C%22%2C%22blockdevice_sda_model%22%3A%22VMware+Virtual+S%22%2C%22mtu_eth0%22%3A%221500%22%2C%22blockdevice_sr0_vendor%22%3A%22NECVMWar%22%2C%22id%22%3A%22root%22%2C%22swapsize_mb%22%3A%22991.99%22%2C%22operatingsystem%22%3A%22CentOS%22%2C%22partitions%22%3A%22sda1mount%2Fbootuuid4d49990f-cbe6-4c9f-8995-659580421cdcsize1024000filesystemext4sda2size40916992filesystemLVM2_member%22%2C%22blockdevice_sr0_model%22%3A%22VMware+IDE+CDR10%22%2C%22boardmanufacturer%22%3A%22Intel+Corporation%22%2C%22swapfree%22%3A%22991.67+MB%22%2C%22kernel%22%3A%22Linux%22%2C%22augeasversion%22%3A%221.0.0%22%2C%22memorysize%22%3A%22482.84+MB%22%2C%22uuid%22%3A%22564D052A-46F1-9907-C0FD-54BA63B3B013%22%2C%22bios_release_date%22%3A%2207%2F31%2F2013%22%2C%22interfaces%22%3A%22eth0%2Clo%22%2C%22ipaddress%22%3A%22192.168.7.130%22%2C%22system_uptime%22%3A%22seconds35738hours9days0uptime9%3A55+hours%22%2C%22operatingsystemmajrelease%22%3A%226%22%2C%22selinux_config_policy%22%3A%22targeted%22%2C%22ipaddress_lo%22%3A%22127.0.0.1%22%2C%22kernelmajversion%22%3A%222.6%22%2C%22sshrsakey%22%3A%22AAAAB3NzaC1yc2EAAAABIwAAAQEA1jZ2bWtW6wrbIkmmGSW6XMAHJ9ngVf8P%2BpQST52xvIwuAarBQ3zmKggxp%2BTPLHbi1qDca1j1YwB3eEB42OAdEyLAudWYbJgdVKlhFNYvMsyIHkue1PIej7DK5uXMYE0ZaZIUHGBGFkKJv9FPZSw07gL9%2F4%2BpAz69yeGf1v5sywcq0Bns21IE6pKnqC%2BnaKX7k4TgnnFJoVNesYWsT9I7HZZ8ynsVvVdnTWb7y9xYTFnzBHaPPzdWA%2BGsVLI4jrbozAYHTzT8Hw0evHJBpcReNxlpFl4%2FnGsap4PD4Uri9PanmAXDLFyaBB5UacLjvBW4QhyA0MBONdU%2BTUDllSzVlw%3D%3D%22%7D%7D""")
			.formParam("""facts_format""", """pson"""))
		.pause(2)
		.exec(http("filemeta")
			.get("""/production/file_metadata/modules/puppetconf/stuff.json?source_permissions=use&links=manage"""))
		.exec(http("filemeta")
			.get("""/production/file_metadata/modules/puppetconf/stuff.json?source_permissions=use&links=manage"""))
		.pause(1)
		.exec(http("report")
			.put("""/production/report/puppet-agent""")
			.headers(headers_108)
      .body(RawFileBody("FOSS370CatalogZero_report.txt")))
					
	val scn = scenario("RecordedSimulation").exec(
		chain_0)

	//setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

        val REPETITION_COUNTER: String = "repetitionCounter"
        val NUM_AGENTS: Int = 20
        val NUM_REPETITIONS: Int = 1000
        val SLEEP_DURATION: FiniteDuration = 5 seconds
        val RAMP_UP_DURATION: FiniteDuration = 10 minutes

        def addSleeps(chain:ChainBuilder, totalNumReps:Int): ChainBuilder = {
          // This is kind of a dirty hack. Here's the deal.
          // In order to simulate real world agent runs, we need to sleep 30 minutes
          // in between each series of agent requests. That can be achieved
          // easily by adding a "pause" to the end of the run.
          // However, if we do that, then after the final series of requests, we'll sleep
          // for 30 minutes before the simulation can end, even though that is entirely
          // unnecessary. Since most of our jenkins jobs are going to run 2-6 sims,
          // that would mean we're sleeping for 1-3 extra hours and uselessly tying up the
          // hardware. Thus, we need to make the sleep conditional based on whether
          // or not we're on the final repetition.
          // Here we've replaced our "pause" with a Gatling "session function",
          // which basically just sets a session variable to check to see if
          // we are on the final repetition, and if not, sleep for 30 mins.
          chain.exec((session: Session) => {
            val repetitionCount = session(REPETITION_COUNTER).asOption[Int].getOrElse(0) + 1
            println("Agent " + session.userId +
              " completed " + repetitionCount + " of " + totalNumReps + " repetitions.")
            session.set(REPETITION_COUNTER, repetitionCount)
          }).doIf((session) => session(REPETITION_COUNTER).as[Int] < totalNumReps) {
            exec((session) => {
              println("This is not the last repetition; sleeping " + SLEEP_DURATION + ".")
              session
            }).pause(SLEEP_DURATION)
          }.doIf((session) => session(REPETITION_COUNTER).as[Int] >= totalNumReps) {
            exec((session) => {
              println("That was the last repetition. Not sleeping.")
              session
            })
          }
        }

        val chainWithFailFast:ChainBuilder =
          // this wrapper causes the agent sims to exit the series of
          // of requests upon the first failure, rather than continuing
          // to send up the remaining requests for the agent run.
          exitBlockOnFail {
            exec(scn)
          }

        val chainWithSleeps:ChainBuilder =
          addSleeps(chainWithFailFast, NUM_REPETITIONS)

        val finalScn = scenario(this.getClass.getSimpleName)
          .repeat(NUM_REPETITIONS) {
            group((session) => this.getClass.getSimpleName) {
              chainWithSleeps
            }
          }.inject(rampUsers(NUM_AGENTS) over RAMP_UP_DURATION)
          .protocols(httpProtocol)

	setUp(finalScn)
}
