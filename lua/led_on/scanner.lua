id  = 0
sda = 2
scl = 1

print("Init I2C");
i2c.setup(id, sda, scl, i2c.SLOW)

function read_reg(dev_addr, reg_addr)
     i2c.start(id)
     i2c.address(id, dev_addr ,i2c.TRANSMITTER)
     i2c.write(id, reg_addr)
     i2c.stop(id)
     i2c.start(id)
     i2c.address(id, dev_addr, i2c.RECEIVER)
     c=i2c.read(id, 1)
     i2c.stop(id)
     return c
end

print("Scanning I2C Bus")
for i=0,127 do
     print("Trying address "..string.format("%02X",i))
     if (string.byte(read_reg(i, 0))==0) then
     print("Device found at address "..string.format("%02X",i))
     end
end

