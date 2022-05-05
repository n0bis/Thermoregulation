from lmt86 import transfer_table

def lmt8x_v2t(model, v):
    vout = transfer_table['vout']
    start = transfer_table['range'][0]

    if v > vout[0] or v < vout[-1]:
        raise ValueError('v out of range')

    # binary search
    left = 0
    right = len(vout) - 1

    while left < right - 1:
        middle = (left + right) // 2

        # found exact value
        if vout[middle] == v:
            return start + middle

        elif vout[middle] > v:
            left = middle

        else:
            right = middle

    return start + float(vout[left] - v) / float(vout[left] - vout[right]) + left


def lmt84_v2t(v):
    """
    Transfer function for LMT84.
    :param v:  Voltage in mV.
    :type v: float
    :return:  Temperature in Celsius.
    :rtype: float
    """
    return lmt8x_v2t('lmt84', v)


def lmt85_v2t(v):
    """
    Transfer function for LMT85.
    :param v:  Voltage in mV.
    :type v: float
    :return:  Temperature in Celsius.
    :rtype: float
    """
    return lmt8x_v2t('lmt85', v)


def lmt86_v2t(v):
    """
    Transfer function for LMT86.
    :param v:  Voltage in mV.
    :type v: float
    :return:  Temperature in Celsius.
    :rtype: float
    """
    return lmt8x_v2t('lmt86', v)


def lmt87_v2t(v):
    """
    Transfer function for LMT87.
    :param v:  Voltage in mV.
    :type v: float
    :return:  Temperature in Celsius.
    :rtype: float
    """
    return lmt8x_v2t('lmt87', v)