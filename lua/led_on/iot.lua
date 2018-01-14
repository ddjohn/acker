led1 = 3
led2 = 4

gpio.mode(led1, gpio.OUTPUT)
gpio.mode(led2, gpio.OUTPUT)

print('Starting webserver...');
srv=net.createServer(net.TCP)

print('Listen webserver...');
srv:listen(80,function(conn)
  conn:on("receive", function(client,request)
	print('Receive message...');
        local buf = "";
        local _, _, method, path, vars = string.find(request, "([A-Z]+) (.+)?(.+) HTTP");
        if(method == nil)then
            _, _, method, path = string.find(request, "([A-Z]+) (.+) HTTP");
        end
        local _GET = {}
        if (vars ~= nil)then
            for k, v in string.gmatch(vars, "(%w+)=(%w+)&*") do
                _GET[k] = v;
            end
        end
        buf = buf.."<h1> ESP8266 Web Server</h1>";
        buf = buf.."<p>GPIO2 <a href=\"?pin=ON\"><button>ON</button></a>&nbsp;<a href=\"?pin=OFF\"><button>OFF</button></a></p>";
        local _on,_off = "",""
        if(_GET.pin == "ON")then
              gpio.write(led2, gpio.LOW);
        elseif(_GET.pin == "OFF")then
              gpio.write(led2, gpio.HIGH);
        elseif(_GET.pin == "on")then
              gpio.write(led2, gpio.LOW);
        elseif(_GET.pin == "off")then
              gpio.write(led2, gpio.HIGH);
        elseif(_GET.pin == "text")then
	      dofile("lcd.lua").cls();
	      dofile("lcd.lua").lcdprint(_GET.text, 1, 0)
        end
        client:send(buf);
        client:close();
        collectgarbage();
    end)
end)

