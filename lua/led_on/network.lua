function wait()
  if wifi.sta.getip() == nil then
    print('IP unavailable, Waiting...')
  else
    tmr.stop(1)
    print('ESP8266 mode is: ' .. wifi.getmode())
    print('MAC address is: ' .. wifi.ap.getmac())
    print('IP is '..wifi.sta.getip())
    dofile('iot.lua')
  end
end

print('Set up wifi...')
wifi.setmode(wifi.STATION)
wifi.sta.config('Felicia','21E3B46A72')
wifi.sta.connect()
tmr.alarm(1, 2500, 1, wait)
