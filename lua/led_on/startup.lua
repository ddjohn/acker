function startup()
  print('Starting...')
  dofile('iot.lua')
end

print('Wait for 30 sec')
dofile("lcd.lua").cls();
-- dofile("lcd.lua").lcdprint("Init()", 1, 0)
dofile("lcd.lua").lcdprint("2018-01-18", 2, 0)
--dofile("lcd.lua").home();
--dofile("lcd.lua").cursor(1);
tmr.alarm(0, 30000, 0, startup)
