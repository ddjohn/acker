function startup()
  print('Starting...')
  dofile('startup.lua')
end

print('Wait for 10 sec')
tmr.alarm(0, 10000, 0, startup)
