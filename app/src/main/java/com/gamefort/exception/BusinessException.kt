package com.gamefort.exception

import com.gamefort.data.model.app.CustomMessage

class BusinessException(val businessMessage: CustomMessage) : Exception()